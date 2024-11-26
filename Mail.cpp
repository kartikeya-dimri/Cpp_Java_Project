#include <iostream>
#include <curl/curl.h>

// SMTP settings for Gmail
const std::string SMTP_URL = "smtp://smtp.gmail.com:587";
const std::string USERNAME = "employeemanagement35@gmail.com"; // Your Gmail email
const std::string PASSWORD = "xvju irrs lgyk ifzk";            // Your App Password

static const char *payload_text[] = {
    "To: nipun02325@gmail.com\r\n",                                        // Recipient's email
    "From: employeemanagement35@gmail.com\r\n",                            // Sender's email
    "Subject: Test Email from C++ using libcurl\r\n",                      // Subject
    "\r\n",                                                                // Empty line to separate headers from body
    "This is a test email sent from a C++ application using libcurl.\r\n", // Body
    nullptr};

// Payload callback function
size_t payload_source(void *ptr, size_t size, size_t nmemb, void *userp)
{
    const char **payload_text = (const char **)userp;
    if ((size == 0) || (nmemb == 0) || ((size * nmemb) < 1))
    {
        return 0;
    }

    if (*payload_text)
    {
        size_t len = strlen(*payload_text);
        memcpy(ptr, *payload_text, len);
        (*payload_text)++;
        return len;
    }
    return 0;
}

int main()
{
    CURL *curl;
    CURLcode res = CURLE_OK;
    struct curl_slist *recipients = nullptr;

    curl = curl_easy_init();
    if (curl)
    {
        curl_easy_setopt(curl, CURLOPT_USERNAME, USERNAME.c_str());
        curl_easy_setopt(curl, CURLOPT_PASSWORD, PASSWORD.c_str());
        curl_easy_setopt(curl, CURLOPT_URL, SMTP_URL.c_str());

        // Enable STARTTLS
        curl_easy_setopt(curl, CURLOPT_USE_SSL, CURLUSESSL_ALL);
        curl_easy_setopt(curl, CURLOPT_MAIL_FROM, USERNAME.c_str());

        // Add recipient
        recipients = curl_slist_append(recipients, "nipun02325@gmail.com");
        curl_easy_setopt(curl, CURLOPT_MAIL_RCPT, recipients);

        // Specify the payload (email body)
        curl_easy_setopt(curl, CURLOPT_READFUNCTION, payload_source);
        curl_easy_setopt(curl, CURLOPT_READDATA, payload_text);
        curl_easy_setopt(curl, CURLOPT_UPLOAD, 1L);

        // Send the message
        res = curl_easy_perform(curl);

        // Check for errors
        if (res != CURLE_OK)
            fprintf(stderr, "curl_easy_perform() failed: %s\n", curl_easy_strerror(res));

        // Clean up
        curl_slist_free_all(recipients);
        curl_easy_cleanup(curl);
    }

    return 0;
}
