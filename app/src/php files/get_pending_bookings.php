<?php
header("Content-Type: application/json");
include "db.php";

try {
    // Fetch all pending bookings with equipment and user details
    // Using booking_date column as per database schema
    $sql = "SELECT 
                b.id,
                b.user_id,
                u.name as user_name,
                b.equipment_id,
                e.name as equipment_name,
                b.quantity,
                b.rental_days,
                b.total_amount,
                b.status,
                b.booking_date as created_at
            FROM bookings b
            LEFT JOIN equipment e ON b.equipment_id = e.id
            LEFT JOIN users u ON b.user_id = u.id
            WHERE b.status = 'pending'
            ORDER BY b.booking_date DESC";
    
    $result = $conn->query($sql);
    $bookings = [];
    
    if ($result) {
        while ($row = $result->fetch_assoc()) {
            $bookings[] = [
                "id" => intval($row['id']),
                "user_id" => intval($row['user_id']),
                "user_name" => $row['user_name'] ?? "Unknown User",
                "equipment_id" => intval($row['equipment_id']),
                "equipment_name" => $row['equipment_name'] ?? "Unknown Equipment",
                "quantity" => intval($row['quantity']),
                "rental_days" => intval($row['rental_days']),
                "total_amount" => floatval($row['total_amount']),
                "status" => $row['status'],
                "created_at" => $row['created_at']
            ];
        }
    }
    
    echo json_encode([
        "status" => "success",
        "bookings" => $bookings
    ]);

} catch (Exception $e) {
    echo json_encode([
        "status" => "error",
        "message" => "Failed to fetch pending bookings: " . $e->getMessage()
    ]);
}
?>

