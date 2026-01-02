<?php
header("Content-Type: application/json; charset=UTF-8");
require_once "db.php"; // this sets up $conn

// Read raw JSON body
$raw = file_get_contents("php://input");
$data = json_decode($raw, true);

// Validate JSON
if (!is_array($data)) {
    echo json_encode([
        "status"  => "error",
        "message" => "Invalid JSON input"
    ]);
    exit;
}

// Read and sanitize fields
$name           = trim($data['name'] ?? '');
$category       = trim($data['category'] ?? '');
$type           = trim($data['type'] ?? '');
$specifications = trim($data['specifications'] ?? '');
$price          = floatval($data['price_per_day'] ?? 0);
$quantity       = intval($data['quantity'] ?? 0);
$availability   = trim($data['availability'] ?? '');

// Basic validation
if (
    $name === '' ||
    $category === '' ||
    $type === '' ||
    $specifications === '' ||
    $price <= 0 ||
    $quantity <= 0 ||
    $availability === ''
) {
    echo json_encode([
        "status"  => "error",
        "message" => "All fields are required and must be valid"
    ]);
    exit;
}

// Prepare INSERT statement
$stmt = $conn->prepare(
    "INSERT INTO equipment
    (name, category, type, specifications, price_per_day, quantity, availability)
    VALUES (?,?,?,?,?,?,?)"
);

if (!$stmt) {
    echo json_encode([
        "status"  => "error",
        "message" => "Prepare failed: " . $conn->error
    ]);
    exit;
}

// Bind parameters: s = string, d = double, i = integer
$stmt->bind_param(
    "ssssdis",
    $name,
    $category,
    $type,
    $specifications,
    $price,
    $quantity,
    $availability
);

// Execute and respond
if ($stmt->execute()) {
    echo json_encode([
        "status"       => "success",
        "message"      => "Equipment added successfully",
        "equipment_id" => $stmt->insert_id
    ]);
} else {
    echo json_encode([
        "status"  => "error",
        "message" => "Database insert failed: " . $stmt->error
    ]);
}

// Cleanup
$stmt->close();
$conn->close();