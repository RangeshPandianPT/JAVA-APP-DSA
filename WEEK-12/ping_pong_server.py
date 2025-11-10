import socket

HOST = '127.0.0.1'   # Localhost
PORT = 8000          # Any free port

server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_socket.bind((HOST, PORT))
server_socket.listen(1)

print("Server: Waiting for client connection...")

conn, addr = server_socket.accept()
print(f"Server: Connected by {addr}")

while True:
    data = conn.recv(1024)
    if not data:
        break

    message = data.decode().strip().lower()
    print(f"Server received: {message}")

    if message == "ping":
        conn.sendall("pong".encode())
        print("Server sent: pong")
    else:
        print("Server dropped message.")

conn.close()
server_socket.close()
print("Server: Connection closed.")
