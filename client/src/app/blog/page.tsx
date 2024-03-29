'use client'
import React, {useEffect, useState} from "react";
import { BlogItemProps } from "@/Utils/Types"; // Import BlogItemProps type
import Image from "next/image";
import {getAllPublicBlogs, getBlogByUserID} from "@/Rest/Blog/BlogGET";

const data = {
    "uploaderId": "660667423568fa1e8e6c6ee1",
    "ownerName": "Sirisha",
    "title": "The Convergence of Tech and Science in Modern Healthcare",
    "description": "Examining the synergistic relationship between technology and science in advancing healthcare solutions.",
    "categories": ["Technology", "Science"],
    "content": "The landscape of healthcare is undergoing a profound transformation, driven by the convergence of technology and science. This synergy is revolutionizing medical research, d and continuity of care. Patients cas data privacy, algorithm bias, and equitable access to healthcare tece, equitable, and patient-centric.",
    "likes": {},
    "commentIds": [],
    "visibility": "public",
    "avgReadingTime": "9m"
};

const BlogPageItem: React.FC<BlogItemProps> = ({ content, ownerName, title, description, date, avgReadingTime, categories }) => {
    let bgColor = "";
    let textColor = "";
    const avgReadingTimeInMinutes = parseInt(avgReadingTime);

    if (!isNaN(avgReadingTimeInMinutes)) {
        if (avgReadingTimeInMinutes > 8) {
            bgColor = "bg-red-600";
            textColor = "text-white";
        } else if (avgReadingTimeInMinutes > 5) {
            bgColor = "bg-yellow-400";
            textColor = "text-black";
        } else {
            bgColor = "bg-green-600";
            textColor = "text-white";
        }
    } else {
        bgColor = "bg-gray-400";
        textColor = "text-black";
    }

    return (
        <div className={`mt-[64px] h-full flex flex-col items-center  min-h-44 w-full`}>
            <Image className={`object-cover h-96 w-[80%] `} src={'/parishkar.jpg'} alt={""} width={1000} height={1000} />
            <div className={`p-4 flex h-full flex-col gap-2 mb-2`}>
                <p className={`cursor-pointer underline italic font-light text-sm font-merri dark:text-neutral-400`}>@{ownerName}</p>
                <h2 className={`font-playfair cursor-pointer font-black dark:text-white  tracking-wide text-4xl`}>{title}</h2>
                {/*<p className={`cursor-pointer font-normal text-neutral-600 dark:text-neutral-400`}>{description}</p>*/}
                <p className={`h-[80%]  cursor-pointer font-normal text-neutral-600 dark:text-neutral-400`}>{content}</p>
                <div className={`flex gap-2 items-center`}>
                    {/*{date &&*/}
                    {/*    // <p className={`text-sm text-primary dark:text-secondary`}>{date.toDateString()}</p>*/}
                    {/*}*/}
                    <p className={`p-1 px-2 text-sm rounded-3xl ${bgColor} ${textColor}`}>{avgReadingTime}</p>
                    <ul className={`flex gap-2`}>
                        {categories && categories.map((category, index) => (
                            <li className={"cursor-pointer flex p-2 bg-neutral-200 dark:bg-neutral-800 dark:text-white rounded-full"} key={index}>{category}</li>
                        ))}
                    </ul>
                </div>
            </div>
        </div>
    );
};

const BlogPage = () => {
    const [blogData, setBlogData] = useState(null); // State to hold blog data

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await getAllPublicBlogs(); // Replace "/api/blog" xwith your API endpoint
                setBlogData(response[0]); // Update state with fetched data
            } catch (error) {
                console.error("Error fetching blog data:", error);
            }
        };

        fetchData(); // Call fetchData function on component mount
    }, []);

    if (!blogData) {
        return <div>Loading...</div>; // Render loading state while fetching data
    }

    return <BlogPageItem {...blogData} />; // Pass fetched data as props to BlogPageItem
};



export default BlogPage;
