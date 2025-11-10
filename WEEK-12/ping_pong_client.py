import socket
import time

HOST = '127.0.0.1'
PORT = 8000

client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client_socket.connect((HOST, PORT))

messages = ["ping", "hello", "ping", "test", "ping"]

for msg in messages:
    print(f"Client sending: {msg}")
    client_socket.sendall(msg.encode())

    # Wait for a response (only expect for 'ping')
    client_socket.settimeout(1)
    try:
        data = client_socket.recv(1024)
        print(f"Client received: {data.decode()}")
    except socket.timeout:
        print("Client: No response (message dropped by server)")
    
    time.sleep(1)

client_socket.close()
print("Client: Connection closed.")
