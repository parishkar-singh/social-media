'use client'
import React, { useEffect, useState } from "react";
import { AxiosResponse } from "axios";
import highClient from "@/Utils/axios";

enum Visibility {
    Public = "public",
    Private = "private"
}

interface BlogFormProps {
    onSubmit: (blog: BlogData) => void;
}

interface BlogData {
    title: string;
    description: string;
    avgReadingTime: string;
    ownerName:string;
    content: string;
    categories: string[];
    visibility: Visibility;
    uploaderId:string
}

const BlogForm: React.FC<BlogFormProps> = ({ onSubmit }) => {
    const [formData, setFormData] = useState<BlogData>({
        uploaderId:"660667423568fa1e8e6c6ee1",
        title: "",
        ownerName:"Parishkar",
        description: "",
        avgReadingTime: "",
        content: "",
        categories: [],
        visibility: Visibility.Public // Default visibility
    });

    const handleChange = (
        e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
    ) => {
        const { name, value } = e.target;
        if (name === "categories") {
            const categories = value.split(",").map((category) => category.trim());
            setFormData((prevState) => ({
                ...prevState,
                categories: categories
            }));
        } else {
            setFormData((prevState) => ({
                ...prevState,
                [name]: value
            }));
        }
    };

    const handleVisibilityChange = (
        e: React.ChangeEvent<HTMLSelectElement>
    ) => {
        const { value } = e.target;
        setFormData((prevState) => ({
            ...prevState,
            visibility: value as Visibility
        }));
    };

    const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        try {
            const requestBody = { ...formData };
            const response: AxiosResponse = await highClient.post(
                "/api/user/blog/660667423568fa1e8e6c6ee1",
                requestBody,
                { withCredentials: true }
            );
            console.log(response.data);
            // Call the onSubmit function passed from the parent component
            onSubmit(formData);
            // Reset form data after successful submission
            setFormData({
                title: "",
                description: "",
                uploaderId:"660667423568fa1e8e6c6ee1",
                avgReadingTime: "",
                content: "",
                ownerName:"Parishkar",
                categories: [],
                visibility: Visibility.Public // Reset to default visibility
            });
        } catch (error) {
            console.error("Error while posting blog data:", error);
        }
    };
    return (
        <form onSubmit={handleSubmit} className="mt-32 max-w-3xl mx-auto px-4 py-8">
            <div className="mb-4">
                <label htmlFor="title" className="block text-sm font-medium text-gray-700">Title</label>
                <input type="text" id="title" name="title" value={formData.title} onChange={handleChange}
                       className="mt-1 p-2 border rounded-md w-full" required/>
            </div>
            <div className="mb-4">
                <label htmlFor="description" className="block text-sm font-medium text-gray-700">Description</label>
                <textarea id="description" name="description" value={formData.description} onChange={handleChange}
                          className="mt-1 p-2 border rounded-md w-full" required/>
            </div>
            <div className="mb-4">
                <label htmlFor="avgReadingTime" className="block text-sm font-medium text-gray-700">Average Reading
                    Time</label>
                <input type="text" id="avgReadingTime" name="avgReadingTime" value={formData.avgReadingTime}
                       onChange={handleChange} className="mt-1 p-2 border rounded-md w-full" required/>
            </div>
            <div className="mb-4">
                <label htmlFor="content" className="block text-sm font-medium text-gray-700">Content</label>
                <textarea id="content" name="content" value={formData.content} onChange={handleChange}
                          className="mt-1 p-2 border rounded-md w-full" required/>
            </div>
            <div className="mb-4">
                <label htmlFor="categories" className="block text-sm font-medium text-gray-700">Categories
                    (comma-separated)</label>
                <input type="text" id="categories" name="categories" value={formData.categories ? formData.categories.join(",") : ""}
                       onChange={handleChange} className="mt-1 p-2 border rounded-md w-full" required/>
            </div>

            <div className="mb-4">
                <label
                    htmlFor="visibility"
                    className="block text-sm font-medium text-gray-700"
                >
                    Visibility
                </label>
                <select
                    id="visibility"
                    name="visibility"
                    value={formData.visibility}
                    onChange={handleVisibilityChange}
                    className="mt-1 p-2 border rounded-md w-full"
                    required
                >
                    {Object.values(Visibility).map((visibility) => (
                        <option key={visibility} value={visibility}>
                            {visibility}
                        </option>
                    ))}
                </select>
            </div>
            <button
                type="submit"
                className="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600"
            >
                Submit
            </button>
        </form>
    );
};

export default BlogForm;
