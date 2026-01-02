<?php
header("Content-Type: application/json");
include "db.php";

// Get POST data
$data = json_decode(file_get_contents("php://input"), true);

$type = trim($data['type'] ?? '');
$message = trim($data['message'] ?? '');
$user_id = isset($data['user_id']) ? intval($data['user_id']) : null;
$send_to_all = isset($data['send_to_all']) ? $data['send_to_all'] : true;

// Validate required fields
if ($message === '') {
    echo json_encode([
        "status" => "error",
        "message" => "Notification message is required"
    ]);
    exit;
}

// Map incoming types to valid database enum values
// Database only accepts: 'booking', 'admin', 'new_equipment'
switch ($type) {
    case 'status_alert':
    case 'reminder':
    case 'promotion':
    case 'admin':
        $type = 'admin'; // All admin-sent notifications use 'admin' type
        break;
    case 'booking':
        $type = 'booking';
        break;
    case 'new_equipment':
        $type = 'new_equipment';
        break;
    default:
        $type = 'admin'; // Default to admin type
}

try {
    if ($send_to_all) {
        // Send to all users - insert with NULL user_id (broadcast to everyone)
        $stmt = $conn->prepare(
            "INSERT INTO notifications (user_id, type, message, status) VALUES (NULL, ?, ?, 'unread')"
        );
        $stmt->bind_param("ss", $type, $message);
        
        if ($stmt->execute()) {
            echo json_encode([
                "status" => "success",
                "message" => "Notification sent to all users",
                "notification_id" => $stmt->insert_id
            ]);
        } else {
            echo json_encode([
                "status" => "error",
                "message" => "Failed to send notification: " . $conn->error
            ]);
        }
        $stmt->close();
    } else {
        // Send to specific user
        if ($user_id === null || $user_id <= 0) {
            echo json_encode([
                "status" => "error",
                "message" => "User ID is required for targeted notifications"
            ]);
            exit;
        }
        
        $stmt = $conn->prepare(
            "INSERT INTO notifications (user_id, type, message, status) VALUES (?, ?, ?, 'unread')"
        );
        $stmt->bind_param("iss", $user_id, $type, $message);
        
        if ($stmt->execute()) {
            echo json_encode([
                "status" => "success",
                "message" => "Notification sent to user",
                "notification_id" => $stmt->insert_id,
                "user_id" => $user_id
            ]);
        } else {
            echo json_encode([
                "status" => "error",
                "message" => "Failed to send notification: " . $conn->error
            ]);
        }
        $stmt->close();
    }
} catch (Exception $e) {
    echo json_encode([
        "status" => "error",
        "message" => "Error: " . $e->getMessage()
    ]);
}
?>

