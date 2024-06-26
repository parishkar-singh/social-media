'use client'
import React, {useEffect, useState} from "react";
import {TrendingBlogItem} from "@/Components/Item/BlogItem";
import {TrendingBlogItemProps} from "@/Utils/Types";
import {generateTrendingBlogTestData} from "@/Utils/Data";
import {TrendingUp} from "lucide-react";
import {getAllPublicBlogs, getAllTrendingBlogs} from "@/Rest/Blog/BlogGET";

const Trending: React.FC = () => {
    const [posts, setPosts] = useState<TrendingBlogItemProps[]>([]);

    useEffect(() => {
        const fetchTrendingData = async () => {
            try {
                if (typeof window !== 'undefined') {
                    const generatedData = await getAllTrendingBlogs();
                    setPosts(generatedData);
                }
            } catch (error) {
                console.error("Error fetching trending data:", error);
            }
        };

        fetchTrendingData();
    }, []);

    return (
        <div className={`p-10 flex flex-col gap-5 `}>
            <div className={`flex`}>
                <TrendingUp className={`text-black dark:text-white `}/>
                <span className={`text-dark dark:text-white`}>&nbsp;Trending </span>
            </div>
            <div className={` grid grid-cols-2  xl:grid-cols-3 `}>
                {posts.map((post, index) => (
                    <div key={index} className={`flex gap-4`}>
                        <span className={`text-2xl text-neutral-200 dark:text-neutral-800 font-bold`}>0{index}</span>
                        <TrendingBlogItem
                            key={index}
                            ownerName={post.ownerName}
                            title={post.title}
                            // date={post.date}
                            avgReadingTime={post.avgReadingTime}
                            categories={post.categories}
                            uploaderId={'parishkar'}/>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default Trending;
