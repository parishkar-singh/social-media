'use client'
import React from "react";
import ThemeToggle from "@/Components/ThemeToggle";
import { darkModeContainerConfig} from "@/Utils/DarkMode";

const Logo:React.FC=()=>{
    return <div className={`flex items-center gap-4 font-black `}>
        <div className={`bg-black dark:bg-white rounded-full w-10 h-10 transform translate-z-20 `}>
        </div>
        <div className={`bg-black dark:bg-white rounded-full w-10 h-10 skew-x-12 skew-y-12 skew-z-[24deg]`}>
        </div>
        <div className={`bg-black dark:bg-white rounded-full w-10 h-10  skew-x-[50deg] `}>
        </div>
        <span className={'font-playfair ml-2 dark:text-white text-2xl'}>High</span>
    </div>
}

const Navbar: React.FC = () => {
    return (
        <div className={`  flex justify-between w-full p-4 `+ darkModeContainerConfig}>
            <Logo/>
            <ThemeToggle/>
        </div>
    )
}

export default Navbar