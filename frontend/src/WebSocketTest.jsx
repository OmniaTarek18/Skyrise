import React, { useEffect } from 'react';
import { Stomp } from '@stomp/stompjs';

const WebSocketTest = () => {
    useEffect(() => {
        const socketUrl = 'http://localhost:8080/notifications';
        const socket = new WebSocket(socketUrl);
        const stompClient = Stomp.over(socket);

        stompClient.connect(
            { withCredentials: true }, // Ensure credentials are sent
            () => {
                console.log('Connected to WebSocket');

                stompClient.subscribe('/topic/user-2', (message) => {
                    console.log('Received message:', JSON.parse(message.body));
                });
            },
            (error) => {
                console.error('Connection failed:', error);
            }
        );

        return () => {
            if (stompClient) stompClient.disconnect();
        };
    }, []);

    return <div>WebSocket Test Component</div>;
};

export default WebSocketTest;
