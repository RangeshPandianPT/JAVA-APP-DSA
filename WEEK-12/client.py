import socket
import threading

# Server configuration
HOST = '127.0.0.1'
PORT = 5555

# Connect to the server
client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
client.connect((HOST, PORT))

# Function to receive messages from server
def receive_messages():
    while True:
        try:
            message = client.recv(1024).decode()
            if message:
                print(f"\n{message}")
        except:
            print("You have been disconnected from the server.")
            client.close()
            break

# Function to send messages to server
def send_messages():
    while True:
        message = input()
        client.send(message.encode())

# Start threads for sending and receiving
receive_thread = threading.Thread(target=receive_messages)
receive_thread.start()

send_thread = threading.Thread(target=send_messages)
send_thread.start()
