
interface User {
    name: string
    username: string
    email: string
    password: string
}

const users: User[] = [
    {
        "name": "Parishkar Singh",
        "username": "Parishkar",
        "email": "parishkar.esko@gmail",
        "password": "StrongPassword123"
    },
    {
        "name": "Sirisha",
        "username": "Sirisha",
        "email": "sirisha@esko.com",
        "password": "StrongPassword123"
    },
    {
        "name": "adhithya",
        "username": "adhithya",
        "email": "charlie@example.com",
        "password": "StrongPassword123"
    },
    {
        "name": "Chinmay",
        "username": "Chinmay",
        "email": "david@example.com",
        "password": "StrongPassword123"
    }
];

async function registerUser(user: User) {
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

users.forEach(async user => {
    await registerUser(user);
});
///
