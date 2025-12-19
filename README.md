# Quran_App

A simple Quran application built with a client-server architecture. Users can browse Quran pages by Surah and authenticate using login credentials.

## Project Structure

```plaintext
Quran_App/
├── Client/          # Client-side code
├── Server/          # Server-side code
├── quran/           # Quran page images
│   ├── 1/           # Folder for Surah 1 (Al-Fatihah)
│   │   └── page1.jpg
│   ├── 2/           # Folder for Surah 2 (Al-Baqarah)
│   │   └── page1.jpg
│   └── ...          # Folders for other Surahs (3, 4, ...)
└── users.txt        # File containing usernames and passwords for login

### quran/ Folder
- Each Surah has its own folder named by its number (1, 2, 3, ...).
- Each folder contains images of the pages of that Surah.
- The server uses this folder structure to search for pages when the client requests a specific Surah or page.

### users.txt
- This file stores usernames and passwords for registered users.
- When a user tries to log in from the client, the server checks this file to validate credentials.

## Usage
1. Run the server code in the `Server/` folder.
2. Run the client code in the `Client/` folder.
3. The client can request specific Surah or pages.
4. Login requests are validated against `users.txt`.
