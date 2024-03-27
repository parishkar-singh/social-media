'use client'
import React, {useEffect, useState} from "react";
import {TrendingBlogItem} from "@/Components/Item/BlogItem";
import {TrendingBlogItemProps} from "@/Utils/Types";
import {generateTrendingBlogTestData} from "@/Utils/Data";
import {TrendingUp} from "lucide-react";

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
        <div className={`p-10 flex flex-col gap-5 `}>
            <div className={`flex`}>
                <TrendingUp className={`text-black dark:text-white `}/>
                <span>&nbsp;Trending </span>
            </div>
            <div className={` grid grid-cols-2  xl:grid-cols-3 `}>
                {posts.map((post, index) => (
                    <div key={index} className={`flex gap-4`}>
                        <span className={`text-2xl text-neutral-200 dark:text-neutral-800 font-bold`}>0{index}</span>
                        <TrendingBlogItem
                            key={index}
                            owner={post.owner}
                            title={post.title}
                            date={post.date}
                            avgReadingTime={post.avgReadingTime}
                            categories={post.categories}
                        />
                    </div>
                ))}
            </div>
        </div>
    );
}

export default Trending;
