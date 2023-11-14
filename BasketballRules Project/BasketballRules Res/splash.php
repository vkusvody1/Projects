<?php

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $responses = array(
        "https://example.com",
        "no",
        "nopush"
    );
    $random_index = array_rand($responses);
    echo $responses[$random_index];
} else {
    echo "Invalid request method.";
}

?>
