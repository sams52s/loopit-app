import React, {useState} from 'react';
import {gql, useMutation} from '@apollo/client';
import {useNavigate} from 'react-router-dom';
import './LoginPage.css';

const LOGIN_MUTATION = gql`
    mutation Login($email: String!, $password: String!) {
        login(email: $email, password: $password)
    }
`;

export default function LoginPage() {
    const [form, setForm] = useState({email: '', password: ''});
    const [login, {data, loading, error}] = useMutation(LOGIN_MUTATION);
    const navigate = useNavigate();

    const handleChange = (e) => {
        const {name, value} = e.target;
        setForm((prev) => ({...prev, [name]: value}));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await login({variables: {email: form.email, password: form.password}});
            navigate('/');
        } catch (err) {
            console.error(err);
        }
    };

    return (
        <div className="login-container">
            <div className="login-form-box">

                <h2 className="login-heading">Welcome Back</h2>
                <p className="login-subheading">Login to continue using Loopit</p>
                <form onSubmit={handleSubmit} className="login-form">
                    <input
                        name="email"
                        type="email"
                        placeholder="Email"
                        value={form.email}
                        onChange={handleChange}
                        className="login-input"
                        required
                    />
                    <input
                        name="password"
                        type="password"
                        placeholder="Password"
                        value={form.password}
                        onChange={handleChange}
                        className="login-input"
                        required
                    />
                    <button type="submit" disabled={loading} className="login-button info-button full-width-button">
                        {loading ? 'Logging in...' : 'Login'}
                    </button>

                </form>
                {error && <p className="login-error">{error.message}</p>}
                {data && <p className="login-success">{data.login}</p>}
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
                        onClick={() => navigate('/register')}
                        className="login-button secondary-button full-width-button"
                    >
                        Register
                    </button>
                </div>
            </div>
        </div>
    );
}