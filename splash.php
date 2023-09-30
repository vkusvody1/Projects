<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $phone_name = $_POST['phone_name'];
    $locale = $_POST['locale'];
    $unique = $_POST['unique'];

    $responses = array(
        "https://example.com",
        "no",
        "nopush"
    );
    $random_index = array_rand($responses);

    $response = $responses[$random_index];

    // запись в отдельный файл
    $data = "Phone Name: $phone_name\nLocale: $locale\nUnique: $unique\nResponse: $response\n";
    file_put_contents('log.txt', $data, FILE_APPEND);

    echo $response;
} else {
    // сообщение об ошибке
    echo "Invalid request method.";
}

?>
