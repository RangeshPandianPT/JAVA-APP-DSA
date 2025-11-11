import socket
import os

# Server configuration
HOST = '127.0.0.1'
PORT = 6000

# Directory where files are stored
FILES_DIR = "server_files"

# Ensure the directory exists
os.makedirs(FILES_DIR, exist_ok=True)

def send_file(client_socket, filename):
    file_path = os.path.join(FILES_DIR, filename)

    if os.path.exists(file_path):
        client_socket.send(b"FOUND")
        with open(file_path, "rb") as f:
            data = f.read()
            client_socket.sendall(data)
        print(f"[FILE SENT] {filename}")
    else:
        client_socket.send(b"NOTFOUND")
        print(f"[FILE NOT FOUND] {filename}")

def start_server():
    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server.bind((HOST, PORT))
    server.listen()

    print(f"[SERVER STARTED] Listening on {HOST}:{PORT}")

    while True:
        client_socket, addr = server.accept()
        print(f"[CONNECTED] {addr}")

        filename = client_socket.recv(1024).decode()
        print(f"[REQUESTED FILE] {filename}")

        send_file(client_socket, filename)

        client_socket.close()

if __name__ == "__main__":
    start_server()
