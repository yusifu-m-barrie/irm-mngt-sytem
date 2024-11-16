import { signIn } from "next-auth/react";
import React, { useState } from "react";
import Navbar from "./Navbar";

const Login = () => {
    const [isSignUp, setIsSignUp] = useState(false);
    const [formData, setFormData] = useState({
        name: "",
        email: "",
        password: "",
    });

    // Handle form data change
    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value,
        });
    };

    // Handle form submission
    const handleSubmit = (e) => {
        e.preventDefault();
        if (isSignUp) {
            // Handle sign-up logic here (e.g., call API)
            console.log("Sign Up Data:", formData);
        } else {
            // Sign In logic
            signIn();
        }
    };

    return (
        <div>
            <Navbar />
            <div className="mt-8 text-center">
                <div className="h-12">
                    <button
                        onClick={signIn}
                        className="rounded bg-blue-600 text-white px-6 py-2 font-semibold"
                    >
                        Sign In
                    </button>
                </div>

                {/* Toggle between Sign Up and Sign In */}
                <div className="mt-8 text-center">
                    <button
                        onClick={() => setIsSignUp(!isSignUp)}
                        className="text-blue-600"
                    >
                        {isSignUp ? "Already have an account? Sign In" : "Don't have an account? Sign Up"}
                    </button>
                </div>

                {/* Sign-Up Form */}
                {isSignUp && (
                    <form onSubmit={handleSubmit} className="mt-8">
                        <div className="mb-4">
                            <label htmlFor="name" className="block text-sm font-medium">
                                Name
                            </label>
                            <input
                                type="text"
                                id="name"
                                name="name"
                                value={formData.name}
                                onChange={handleChange}
                                className="mt-2 p-2 w-full border rounded"
                                required
                            />
                        </div>

                        <div className="mb-4">
                            <label htmlFor="email" className="block text-sm font-medium">
                                Email
                            </label>
                            <input
                                type="email"
                                id="email"
                                name="email"
                                value={formData.email}
                                onChange={handleChange}
                                className="mt-2 p-2 w-full border rounded"
                                required
                            />
                        </div>

                        <div className="mb-4">
                            <label htmlFor="password" className="block text-sm font-medium">
                                Password
                            </label>
                            <input
                                type="password"
                                id="password"
                                name="password"
                                value={formData.password}
                                onChange={handleChange}
                                className="mt-2 p-2 w-full border rounded"
                                required
                            />
                        </div>

                        <div className="mb-4">
                            <button
                                type="submit"
                                className="w-full rounded bg-green-600 text-white px-6 py-2 font-semibold"
                            >
                                Sign Up
                            </button>
                        </div>
                    </form>
                )}
            </div>
        </div>
    );
};

export default Login;
