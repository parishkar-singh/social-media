const users: Array<{ name: string, username: string, email: string, password: string, roles: string[] }> = [
    {
        "name": "Alice Smith",
        "username": "alice",
        "email": "alice@example.com",
        "password": "StrongPassword123",
        "roles": ["USER"]
    },
    {
        "name": "Bob Johnson",
        "username": "bob",
        "email": "bob@example.com",
        "password": "AnotherPassword123",
        "roles": ["USER"]
    },
    {
        "name": "Charlie Brown",
        "username": "charlie",
        "email": "charlie@example.com",
        "password": "YetAnotherPassword123",
        "roles": ["USER"]
    },
    {
        "name": "David Lee",
        "username": "david",
        "email": "david@example.com",
        "password": "Password123",
        "roles": ["USER"]
    }
];

// Function to make a POST request to the API
async function registerUser(user: { name: string, username: string, email: string, password: string, roles: string[] }) {
    try {
        const response = await fetch('http://localhost:8080/auth/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        });

        if (!response.ok) {
            throw new Error('Failed to register user');
        }

        const data = await response.json();
        console.log('User registered successfully:', data);
    } catch (error) {
        console.error('Error registering user:');
    }
}

// Iterate over the array of users and register each one
users.forEach(user => {
    registerUser(user);
});
