// Target the chat elements
const chatMessages = document.getElementById("chat-messages");
const chatInput = document.getElementById("chat-input");
const sendButton = document.getElementById("send-button");

// Function to display a chat message
function displayMessage(message, isUser = false) {
    const messageElement = document.createElement("li");
    messageElement.classList.add("chat-message");
    messageElement.classList.add(isUser ? "user" : "ai");
    messageElement.textContent = message;
    chatMessages.appendChild(messageElement);
    // Scroll to the bottom of the chat window
    chatMessages.scrollTop = chatMessages.scrollHeight;
}

// Handle send button click
sendButton.addEventListener("click", async () => {
    const userMessage = chatInput.value;
    chatInput.value = ""; // Clear input field
    displayMessage(userMessage, true); // Display user's message

    // Send message to backend and display AI response
    try {
        const response = await fetch("/sendMessage", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ message: userMessage })
        });

        const data = await response.json();
        const aiResponse = data.response;
        displayMessage(aiResponse);
    } catch (error) {
        console.error("Error sending message:", error);
        displayMessage("Error: Could not send message. Please try again.");
    }
});
