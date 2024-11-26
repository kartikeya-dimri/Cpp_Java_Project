#include <iostream>
#include <curl/curl.h>

// Function to send an email
bool sendEmail(const std::string &recipient, const std::string &subject, const std::string &body) {
    // SMTP settings for Gmail
    const std::string SMTP_URL = "smtp://smtp.gmail.com:587";
    const std::string USERNAME = "employeemanagement35@gmail.com"; // Your Gmail email
    const std::string PASSWORD = "xvju irrs lgyk ifzk";     // Your App Password

    // Payload with email headers and body
    std::string payload = "To: " + recipient + "\r\n" +
                          "From: " + USERNAME + "\r\n" +
                          "Subject: " + subject + "\r\n" +
                          "\r\n" + 
                          body + "\r\n";

    // Payload callback function
    auto payload_source = [](void *ptr, size_t size, size_t nmemb, void *userp) -> size_t {
        std::string *payload = (std::string *)userp;
        if (payload->empty()) return 0;

        size_t len = payload->size();
        if (len > size * nmemb) len = size * nmemb;

        memcpy(ptr, payload->c_str(), len);
        payload->erase(0, len);
        return len;
    };

    CURL *curl;
    CURLcode res = CURLE_OK;
    struct curl_slist *recipients = nullptr;
    bool success = false;

    curl = curl_easy_init();
    if (curl) {
        curl_easy_setopt(curl, CURLOPT_USERNAME, USERNAME.c_str());
        curl_easy_setopt(curl, CURLOPT_PASSWORD, PASSWORD.c_str());
        curl_easy_setopt(curl, CURLOPT_URL, SMTP_URL.c_str());
        curl_easy_setopt(curl, CURLOPT_USE_SSL, CURLUSESSL_ALL);
        curl_easy_setopt(curl, CURLOPT_MAIL_FROM, USERNAME.c_str());

        // Add recipient
        recipients = curl_slist_append(recipients, recipient.c_str());
        curl_easy_setopt(curl, CURLOPT_MAIL_RCPT, recipients);

        // Specify the payload (email body)
        curl_easy_setopt(curl, CURLOPT_READFUNCTION, payload_source);
        curl_easy_setopt(curl, CURLOPT_READDATA, &payload);
        curl_easy_setopt(curl, CURLOPT_UPLOAD, 1L);

        // Send the message
        res = curl_easy_perform(curl);

        // Check if sending succeeded
        if (res == CURLE_OK) {
            success = true;
        } else {
            std::cerr << "Failed to send email: " << curl_easy_strerror(res) << std::endl;
        }

        // Clean up
        curl_slist_free_all(recipients);
        curl_easy_cleanup(curl);
    }

    return success;
}
int main() {
    // Example of calling the email function
    if (sendEmail("nipun02325@gmail.com", "Test Email from C++", "This is a test email sent using libcurl in C++")) {
        std::cout << "Email sent successfully!" << std::endl;
    } else {
        std::cerr << "Failed to send email." << std::endl;
    }
    
    return 0;
}
