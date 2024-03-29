'use client'
import React from "react";
import {addTransition} from "@/Utils/DarkMode";
import {useRouter} from "next/navigation";

const NavbarLinks:React.FC=()=>{
    const router = useRouter();
    return (
        <div className={`flex gap-2 text-black font-poppins  dark:text-white select-none items-center`}>

            <a  href={'/'} className={`cursor-pointer`}>Home</a>
            <a  href={'/explore'} className={`cursor-pointer`}>Explore</a>
            <a  href={'/feed'} className={`cursor-pointer`}>Feed</a>
            <a  href={'/auth/post'} className={`cursor-pointer`}>Write</a>
            <a  href={'/auth/signin'}  className={addTransition+` cursor-pointer bg-neutral-200 dark:bg-neutral-900 text-black dark:text-white font-poppins font-semibold rounded-3xl py-1 px-2`}>Get Started</a>
        </div>
    )
}

export default NavbarLinks