<?php
// Suppress PHP errors from being outputted with HTML tags
error_reporting(0);
ini_set('display_errors', 0);

header("Content-Type: application/json");

// Include database connection
include "db.php";

// Initialize default values
$total_items = 0;
$total_bookings = 0;
$pending_approvals = 0;
$low_stock_alerts = 0;

try {
    // Get total equipment count
    $result = $conn->query("SELECT COUNT(*) as count FROM equipment");
    if ($result) {
        $row = $result->fetch_assoc();
        $total_items = intval($row['count']);
    }

    // Get total bookings count - check if table exists first
    $result = $conn->query("SELECT COUNT(*) as count FROM bookings");
    if ($result) {
        $row = $result->fetch_assoc();
        $total_bookings = intval($row['count']);
    }

    // Get pending approvals count
    $result = $conn->query("SELECT COUNT(*) as count FROM bookings WHERE status = 'pending'");
    if ($result) {
        $row = $result->fetch_assoc();
        $pending_approvals = intval($row['count']);
    }

    // Get low stock alerts (items with quantity < 10)
    $result = $conn->query("SELECT COUNT(*) as count FROM equipment WHERE quantity < 10");
    if ($result) {
        $row = $result->fetch_assoc();
        $low_stock_alerts = intval($row['count']);
    }

    echo json_encode([
        "status" => "success",
        "total_items" => $total_items,
        "total_bookings" => $total_bookings,
        "pending_approvals" => $pending_approvals,
        "low_stock_alerts" => $low_stock_alerts
    ]);

} catch (Exception $e) {
    echo json_encode([
        "status" => "error",
        "message" => "Failed to fetch dashboard stats: " . $e->getMessage()
    ]);
}
?>
