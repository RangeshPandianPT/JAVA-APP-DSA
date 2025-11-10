import socket
import threading
import os

HOST = '127.0.0.1'
PORT = 5000
BUFFER_SIZE = 1024
FILE_TO_SEND = 'sample.txt'

def server():
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind((HOST, PORT))
    server_socket.listen(1)
    print("Server: Waiting for client connection...")

    conn, addr = server_socket.accept()
    print(f"Server: Connected to {addr}")

    with open('received_file.txt', 'wb') as f:
        while True:
            data = conn.recv(BUFFER_SIZE)
            if not data:
                break
            f.write(data)
    print("Server: File received and saved as 'received_file.txt'")
    conn.close()
    server_socket.close()

def client():
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client_socket.connect((HOST, PORT))
    print("Client: Connected to server")

    if not os.path.exists(FILE_TO_SEND):
        with open(FILE_TO_SEND, 'w') as f:
            f.write("This is a sample text file.\nSent using TCP socket programming.\n")

    with open(FILE_TO_SEND, 'rb') as f:
        for chunk in iter(lambda: f.read(BUFFER_SIZE), b''):
            client_socket.sendall(chunk)
    print("Client: File sent successfully")
    client_socket.close()

if __name__ == "__main__":
    server_thread = threading.Thread(target=server)
    client_thread = threading.Thread(target=client)

    server_thread.start()
    client_thread.start()

    server_thread.join()
    client_thread.join()
