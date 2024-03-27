'use client'
import React, { useState, useEffect } from "react";
import BlogItem from "@/Components/Item/BlogItem";

interface BlogItemProps {
    title: string;
    owner: string;
    description: string;
    date: Date;
    avgReadingTime: string;
    category: string[];
}

function generateTestData(): BlogItemProps[] {
    const titles = [
        "The Art of Coding",
        "Exploring the World of Machine Learning",
        "Healthy Living: Tips for a Better Lifestyle",
        "The Culinary Journey: A Food Lover's Guide",
        "Mastering React: A Comprehensive Guide",
        "Travel Diaries: Adventures Across Continents"
    ];

    const owners = [
        "John Doe",
        "Jane Smith",
        "Alice Johnson",
        "Michael Brown",
        "Emily Wilson",
        "David Lee"
    ];

    const descriptions = [
        "Discover the beauty of coding and unleash your creativity with our comprehensive guide. Explore various programming languages, frameworks, and tools to become a proficient coder.",
        "Dive deep into the fascinating realm of machine learning and artificial intelligence. Learn about algorithms, models, and techniques used to build intelligent systems.",
        "Transform your life with simple yet effective health tips. Explore nutrition, exercise, mindfulness, and holistic wellness practices to achieve a better lifestyle.",
        "Embark on a mouthwatering journey through different cuisines from around the world. Explore recipes, cooking techniques, and cultural influences on food.",
        "Learn React from scratch and become a front-end ninja. Master the fundamentals, advanced concepts, and best practices for building modern web applications.",
        "Experience thrilling adventures and wanderlust tales from around the globe. Join us as we explore exotic destinations, uncover hidden gems, and embrace the spirit of travel."
    ];


    const dates = [
        new Date(2023, 0, 1),
        new Date(2023, 3, 15),
        new Date(2023, 6, 30),
        new Date(2023, 9, 10),
        new Date(2023, 11, 25),
        new Date(2023, 8, 5)
    ];

    const avgReadingTimes = ["5 minutes", "10 minutes", "15 minutes", "20 minutes"];

    const categories = [
        ["Technology", "Programming"],
        ["Travel", "Food", "Adventure"],
        ["Health", "Fitness", "Wellness"],
        ["Education", "Learning", "Self-Improvement"],
        ["Cooking", "Recipes", "Food Culture"],
        ["React", "JavaScript", "Frontend Development"]
    ];

    const randomIndex = (arr: any[]) => Math.floor(Math.random() * arr.length);

    const blogs: BlogItemProps[] = [];
    for (let i = 0; i < 10; i++) {
        const blog: BlogItemProps = {
            title: titles[randomIndex(titles)],
            owner: owners[randomIndex(owners)],
            description: descriptions[randomIndex(descriptions)],
            date: dates[randomIndex(dates)],
            avgReadingTime: avgReadingTimes[randomIndex(avgReadingTimes)],
            category: categories[randomIndex(categories)]
        };
        blogs.push(blog);
    }
    return blogs;
}
const Articles: React.FC = () => {
    const [posts, setPosts] = useState<BlogItemProps[]>([]);
    useEffect(() => {
        if (typeof window !== 'undefined') {
            setPosts(generateTestData());
        }
    }, []);

    return (
        <div>
            {posts.map((post, index) => (
                <BlogItem
                    key={index}
                    owner={post.owner}
                    title={post.title}
                    description={post.description}
                    date={post.date.toDateString()}
                    avgReadingTime={post.avgReadingTime}
                    categories={post.category}
                />
            ))}
        </div>
    );
};

export default Articles