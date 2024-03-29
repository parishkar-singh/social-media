'use client'
import React from "react";
import Image from "next/image";
import {darkModeBottomSeperator} from "@/Utils/DarkMode";
import {BlogItemProps, TrendingBlogItemProps} from "@/Utils/Types";



export const TrendingBlogItem: React.FC<TrendingBlogItemProps> = ({
                                                       owner,
                                                       title,
                                                       date,
                                                       avgReadingTime,
                                                   }) => {
    return (
        <div className={`flex items-center justify-between gap-2  min-h-24 w-3/4 `}>
            <div className={`h-10 w-10 bg-primary rounded-full overflow-hidden `}>
                <Image className={`h-10 w-10 object-cover`} src={'/manAllDressedUp.jpg'} alt={"idk"} width={220} height={220}/>
            </div>
            <div className={`flex flex-col`}>
                <p className={`cursor-pointer underline italic font-light text-[10px] font-merri dark:text-neutral-400`}>@{owner}</p>
                <h2 className={`cursor-pointer font-black dark:text-white font-poppins tracking-wide text-sm`}>{title}</h2>
                <div className={`flex gap-2 items-center`}>

                    <p className={`text-sm text-primary dark:text-third`}>{date.toDateString()}</p>

                    <p className={` px-2 text-sm bg-primary  dark:bg-neutral-500 dark:text-white rounded-3xl`}>{avgReadingTime}</p>

                </div>
            </div>
        </div>)
}
export const BlogItem: React.FC<BlogItemProps> = ({
                                                      owner,
                                                      title,
                                                      description,
                                               date,
                                               avgReadingTime,
                                               categories
                                           }) => {
    let bgColor = "";
    let textColor = "";

    const avgReadingTimeInMinutes = parseInt(avgReadingTime); // Convert avgReadingTime to number

    if (!isNaN(avgReadingTimeInMinutes)) { // Ensure avgReadingTimeInMinutes is a valid number
        if (avgReadingTimeInMinutes > 8) {
            bgColor = "bg-red-600";
            textColor = "text-white";
        } else if (avgReadingTimeInMinutes > 5) {
            bgColor = "bg-yellow-400";
            textColor = "text-black"; // You can adjust the text color based on your preference
        } else {
            bgColor = "bg-green-600";
            textColor = "text-white";
        }
    } else {
        // Handle the case where avgReadingTime is not a valid number
        bgColor = "bg-gray-400"; // Default background color
        textColor = "text-black"; // Default text color
    }
    return (
        <div className={`flex items-center justify-between  min-h-44 w-full border-b dark:border-b dark:border-b-neutral-800 `}>
            <div className={`flex flex-col gap-2 mb-2`}>
                <p className={`cursor-pointer underline italic font-light text-sm font-merri dark:text-neutral-400`}>@{owner}</p>
                <h2 className={`cursor-pointer font-black dark:text-white font-poppins tracking-wide text-2xl`}>{title}</h2>
                <p className={`cursor-pointer font-normal text-neutral-600 dark:text-neutral-400`}>{description}</p>
                <div className={`flex gap-2 items-center`}>
                    {date&&
                    <p className={`text-sm text-primary dark:text-secondary`}>{date.toDateString()}</p>
                    }
                    <p className={`p-1 px-2 text-sm rounded-3xl ${bgColor} ${textColor}`}>{avgReadingTime}</p>
                    <ul className={`flex gap-2`}>
                        {categories && categories.map((category, index) => (
                            <li className={"cursor-pointer flex  p-2 bg-neutral-200 dark:bg-neutral-800 dark:text-white rounded-full "}
                                key={index}>{category}</li>
                        ))}
                    </ul>
                </div>
            </div>
            <Image className={`object-cover rounded-3xl h-36 min-w-44`} src={'/parishkar.jpg'} alt={""} width={200}
                   height={200}/>
        </div>
    );
};
