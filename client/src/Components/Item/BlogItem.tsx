import React from "react";
import Image from "next/image";

interface BlogItemProps {
    owner: string;
    title: string;
    description: string;
    date: string;
    avgReadingTime: string;
    categories: string[];
}

const BlogItem: React.FC<BlogItemProps> = ({
                                               owner,
                                               title,
                                               description,
                                               date,
                                               avgReadingTime,
                                               categories
                                           }) => {
    return (
        <div className={`flex items-center justify-between border border-b-neutral-100 h-32 w-3/4`}>
            <div className={`flex flex-col`}>
            <p className={`font-light text-sm font-merri `}>{owner}</p>
            <h2 className={`font-black  font-playfair tracking-wide text-xl`}>{title}</h2>
            <p className={`font-normal text-neutral-600`}>{description}</p>
            <div className={`flex`}>
                <p>{date}</p>
                <p>&nbsp;{avgReadingTime}</p>
                <ul className={`flex`}>
                    {categories && categories.map((category, index) => (
                        <li className={"flex bg-neutral-200 rounded-full"} key={index}>{category}</li>
                    ))}
                </ul>
            </div>
            </div>
            <Image className={`object-cover rounded-3xl h-32 w-42`} src={'/parishkar.jpg'} alt={""} width={200} height={200}/>
        </div>
    );
};

export default BlogItem;
