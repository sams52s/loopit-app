import React, {useState} from 'react';
import {gql, useMutation} from '@apollo/client';
import {useNavigate} from 'react-router-dom';
import './RegisterPage.css';

const CREATE_USER_MUTATION = gql`
    mutation CreateUser($input: CreateUserInput!) {
        createUser(input: $input) {
            id
            username
            email
        }
    }
`;

export default function RegisterPage() {
    const [form, setForm] = useState({
        username: '',
        email: '',
        password: '',
        phoneNumber: '',
        address: ''
    });

    const [createUser, {data, loading, error}] = useMutation(CREATE_USER_MUTATION);
    const navigate = useNavigate();

    const handleChange = (e) => {
        const {name, value} = e.target;
        setForm((prev) => ({...prev, [name]: value}));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await createUser({
                variables: {
                    input: {
                        username: form.username,
                        email: form.email,
                        password: form.password,
                        phoneNumber: form.phoneNumber,
                        address: form.address
                    }
                }
            });
            navigate('/login');
        } catch (err) {
            console.error(err);
        }
    };

    return (
        <div className="register-container">
            <div className="register-form-box">
                <h2 className="register-heading">Create Your Account</h2>
                <p className="register-subheading">Register to start renting and sharing with Loopit</p>
                <form onSubmit={handleSubmit} className="register-form">
                    <input
                        name="username"
                        placeholder="Username"
                        value={form.username}
                        onChange={handleChange}
                        className="register-input"
                        required
                    />
                    <input
                        name="email"
                        type="email"
                        placeholder="Email"
                        value={form.email}
                        onChange={handleChange}
                        className="register-input"
                        required
                    />
                    <input
                        name="password"
                        type="password"
                        placeholder="Password"
                        value={form.password}
                        onChange={handleChange}
                        className="register-input"
                        required
                    />
                    <input
                        name="phoneNumber"
                        placeholder="Phone Number"
                        value={form.phoneNumber}
                        onChange={handleChange}
                        className="register-input"
                        required
                    />
                    <input
                        name="address"
                        placeholder="Address"
                        value={form.address}
                        onChange={handleChange}
                        className="register-input"
                        required
                    />
                    <button type="submit" disabled={loading} className="register-button info-button full-width-button">
                        {loading ? 'Creating...' : 'Register'}
                    </button>
                </form>
                {error && <p className="register-error">{error.message}</p>}
                {data && <p className="register-success">User {data.createUser.username} registered!</p>}
                <div className="login-button-group">
                    <button
                        type="button"
                        onClick={() => navigate('/')}
                        className="login-button secondary-button full-width-button"
                    >
                        Home
                    </button>
                    <button
                        type="button"
                        onClick={() => navigate('/login')}
                        className="login-button secondary-button full-width-button"
                    >
                        Login
                    </button>
                </div>
            </div>

        </div>
    );
}
