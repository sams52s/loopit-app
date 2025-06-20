import React, {useEffect, useState} from 'react';
import {Link, useNavigate} from 'react-router-dom';

export default function HomePage() {
    const navigate = useNavigate();
    const [user, setUser] = useState(null);

    useEffect(() => {
        const storedUser = localStorage.getItem('user');
        if (storedUser) {
            setUser(JSON.parse(storedUser));
        }
    }, []);

    const handleLogout = () => {
        localStorage.removeItem('user');
        setUser(null);
        navigate('/');
    };

    return (
        <div className="home-page" style={{padding: '20px', position: 'relative'}}>
            <div style={{position: 'absolute', top: 10, right: 20}}>
                {user ? (
                    <button onClick={handleLogout}>Logout</button>
                ) : (
                    <>
                        <Link to="/login">Login</Link> | <Link to="/register">Register</Link>
                    </>
                )}
            </div>
            <h1>Welcome to Loopit</h1>
            <p>A modern social marketplace to rent, buy, and share products.</p>
            {user && <p style={{fontWeight: 'bold'}}>Hi{user.username}! </p>}
        </div>
    );
}
