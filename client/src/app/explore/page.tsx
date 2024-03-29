'use client'
import React, {useEffect, useState} from "react";
import {Compass} from "lucide-react";
import {BlogItemProps} from "@/Utils/Types";
import {getAllPublicBlogs} from "@/Rest/Blog/BlogGET";
import {ExploreBlogItem} from "@/Components/Item/BlogItem";

const Explore=()=>{
    const [explorePosts, setExplorePosts] = useState<BlogItemProps[]>([]);

    useEffect(() => {
        const fetchBlogData = async () => {
            try {
                const response =await getAllPublicBlogs();
                // console.log(response)
                // const response =  generateBlogTestData();
                setExplorePosts(response);

            } catch (error) {
                console.error("Error fetching blog data:", error);
            }
        };

        if (typeof window !== 'undefined') {
            fetchBlogData();
        }
    }, []);
    const ButtonStyles=" bg-neutral-200 dark:bg-neutral-800 dark:text-white  rounded-full p-2 px-2"
    return(
        <div className={`mt-[64px] p-10 flex flex-col w-full h-screen gap-16 items-center `}>
            <div className={`flex gap-2`}>
                <button className={ButtonStyles + " flex gap-2"}><Compass/> Explore Topics</button>
                <button className={ButtonStyles}>Programming</button>
                <button className={ButtonStyles}>Software Development</button>
                <button className={ButtonStyles}>Technology</button>
                <button className={ButtonStyles}>Self Improvement</button>
                <button className={ButtonStyles}>Javascript</button>
            </div>
            <div className={`flex flex-col  items-center gap-5`}>
                <h1 className={`text-5xl dark:text-white font-merri font-black`}>Programming</h1>
                <span className={`text-neutral-600 dark:text-neutral-300`}>Topic · 6.3M Followers · 389K Stories</span>
                <button className={"bg-black text-white dark:text-black dark:bg-white rounded-full p-2 px-4"}>Follow
                </button>
            </div>
            <hr/>
            <div className={`flex flex-col`}>
                <h1 className={`dark:text-white font-merri font-bold text-2xl`}>Recommended stories</h1>
            </div>
            <div className={`p-10 w-full grid grid-cols-3  gap-5 `}>
                {explorePosts.map((post, index) => (
                    <ExploreBlogItem
                        key={index}
                        ownerName={post.ownerName}
                        title={post.title}
                        description={post.description}
                        // date={post.date}
                        avgReadingTime={post.avgReadingTime}
                        categories={post.categories}
                        blogPic={"/"} likes={[{}, {}]} uploaderId={"6606301f69c2c26107a6baaf"}/>
                ))}
            </div>
        </div>
    )
}
export default Explore