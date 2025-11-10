import socket

HOST = '127.0.0.1'  # localhost
PORT = 7000

client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client_socket.connect((HOST, PORT))

message = "hello server, convert me to uppercase"
print(f"Client sending: {message}")
client_socket.sendall(message.encode())

data = client_socket.recv(1024)
print(f"Client received: {data.decode()}")

client_socket.close()
