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
            <div className={`h-10 w-10 bg-primary rounded-full overflow-hidden flex items-center justify-center`}>
                <Image className={`h-10 w-10 object-cover`} src={'/manAllDressedUp.jpg'} alt={"idk"} width={220} height={220}/>
            </div>


            <div className={`flex flex-col  mb-2`}>
                <p className={`cursor-pointer underline italic font-light text-[10px] font-merri dark:text-neutral-400`}>@{owner}</p>
                <h2 className={`cursor-pointer font-black dark:text-white font-poppins tracking-wide text-sm`}>{title}</h2>
                <div className={`flex gap-2 items-center`}>
                    <p className={`text-sm dark:text-primary`}>{date.toDateString()}</p>
                    <p className={` px-2 text-sm bg-teal-500 dark:bg-neutral-500 dark:text-white rounded-3xl`}>{avgReadingTime}</p>

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
    return (
        <div className={`flex items-center justify-between  min-h-44 w-3/4 border-b  `}>
            <div className={`flex flex-col gap-2 mb-2`}>
                <p className={`cursor-pointer underline italic font-light text-sm font-merri dark:text-neutral-400`}>@{owner}</p>
                <h2 className={`cursor-pointer font-black dark:text-white font-poppins tracking-wide text-2xl`}>{title}</h2>
                <p className={`cursor-pointer font-normal text-neutral-600 dark:text-neutral-400`}>{description}</p>
                <div className={`flex gap-2 items-center`}>
                    <p className={`text-sm dark:text-primary`}>{date.toDateString()}</p>
                    <p className={`p-1 px-2 text-sm bg-teal-500 dark:bg-teal-700 dark:text-white rounded-3xl`}>{avgReadingTime}</p>
                    <ul className={`flex gap-2`}>
                        {categories && categories.map((category, index) => (
                            <li className={"cursor-pointer flex  p-2 bg-neutral-200 dark:bg-white rounded-full "}
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
