'use client'
import React, { useEffect, useState } from "react";
import { BlogItem } from "@/Components/Item/BlogItem";
import { generateBlogTestData } from "@/Utils/Data";
import { BlogItemProps } from "@/Utils/Types";
import {getAllPublicBlogs} from "@/Rest/Blog/BlogGET";


const Articles: React.FC = () => {
    const [posts, setPosts] = useState<BlogItemProps[]>([]);

    useEffect(() => {
        const fetchBlogData = async () => {
            try {
                const response =await getAllPublicBlogs();
                console.log(response)
                // const response =  generateBlogTestData();
                setPosts(response);

            } catch (error) {
                console.error("Error fetching blog data:", error);
            }
        };

        if (typeof window !== 'undefined') {
            fetchBlogData();
        }
    }, []);
    return (
        <div className={`p-10 flex w-2/3  flex-col gap-5 `}>
            {posts.map((post, index) => (
                <BlogItem
                    key={index}
                    owner={post.owner}
                    title={post.title}
                    description={post.description}
                    // date={post.date}
                    avgReadingTime={post.avgReadingTime}
                    categories={post.categories}
                  blogPic={"/"} likes={[{},{}]} uploaderId={"6606301f69c2c26107a6baaf"}/>
            ))}
        </div>
    );
};

export default Articles;
