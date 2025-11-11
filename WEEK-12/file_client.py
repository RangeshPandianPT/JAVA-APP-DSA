import socket
import os

# Server configuration
HOST = '127.0.0.1'
PORT = 6000

# Directory to save received files
SAVE_DIR = "client_downloads"

# Ensure the directory exists
os.makedirs(SAVE_DIR, exist_ok=True)

def request_file(filename):
    client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client.connect((HOST, PORT))

    client.send(filename.encode())
    status = client.recv(1024).decode()

    if status == "FOUND":
        file_data = b""
        while True:
            data = client.recv(1024)
            if not data:
                break
            file_data += data

        with open(os.path.join(SAVE_DIR, filename), "wb") as f:
            f.write(file_data)

        print(f"[FILE RECEIVED] {filename} saved in {SAVE_DIR}")
    else:
        print("[ERROR] File not found on server.")

    client.close()

if __name__ == "__main__":
    # Example filename (change as needed)
    filename = "example.txt"
    request_file(filename)
