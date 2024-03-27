'use client'
import React, { useEffect, useState } from "react";
import { TrendingBlogItem } from "@/Components/Item/BlogItem";
import { TrendingBlogItemProps } from "@/Utils/Types";
import { generateTrendingBlogTestData } from "@/Utils/Data";

const Trending: React.FC = () => {
    const [posts, setPosts] = useState<TrendingBlogItemProps[]>([]);

    useEffect(() => {
        const fetchTrendingData = async () => {
            try {
                if (typeof window !== 'undefined') {
                    const generatedData = await generateTrendingBlogTestData();
                    setPosts(generatedData);
                    // console.log(generatedData); // Log the generated data to verify
                }
            } catch (error) {
                console.error("Error fetching trending data:", error);
            }
        };

        fetchTrendingData();
    }, []); // Empty dependency array to run effect only once

    return (
        <div className={`p-10 grid grid-cols-2  xl:grid-cols-3  `}>
            {posts.map((post, index) => (
                <TrendingBlogItem
                    key={index}
                    owner={post.owner}
                    title={post.title}
                    date={post.date}
                    avgReadingTime={post.avgReadingTime}
                    categories={post.categories}
                />
            ))}
        </div>
    );
}

export default Trending;
