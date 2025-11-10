import socket
import threading

HOST = '127.0.0.1'
PORT = 6000
BUFFER_SIZE = 1024

def udp_server():
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    server_socket.bind((HOST, PORT))
    print("UDP Server: Waiting for messages...")

    while True:
        data, addr = server_socket.recvfrom(BUFFER_SIZE)
        if data.decode().lower() == 'exit':
            print("UDP Server: Shutting down.")
            break
        print(f"Server received from {addr}: {data.decode()}")
        reply = f"Message received: {data.decode()}"
        server_socket.sendto(reply.encode(), addr)

    server_socket.close()

def udp_client():
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    message = "Hello, UDP Server!"
    client_socket.sendto(message.encode(), (HOST, PORT))
    data, _ = client_socket.recvfrom(BUFFER_SIZE)
    print(f"Client received: {data.decode()}")

    client_socket.sendto("exit".encode(), (HOST, PORT))
    client_socket.close()

if __name__ == "__main__":
    server_thread = threading.Thread(target=udp_server)
    client_thread = threading.Thread(target=udp_client)

    server_thread.start()
    client_thread.start()

    client_thread.join()
    server_thread.join()
