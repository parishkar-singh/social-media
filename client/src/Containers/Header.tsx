import React from "react";
import {darkModeContainerConfig} from "@/Utils/DarkMode";

const Header: React.FC = () => {
    return (
        <div className={'flex flex-col h-[400px] w-full '+ darkModeContainerConfig}>
            <div className={`py-24 px-12 flex flex-col`}>
                <span className={`text-6xl font-bold font-playfair dark:text-violet-500`}>Experience Reading</span>
                <span className={`text-2xl font-playfair tracking-wide dark:text-white `}>Learn, read, think etc</span>
            <button className={`mt-20 w-52 py-2 font-poppins font-bold bg-black text-white dark:bg-white dark:text-black rounded-3xl `}>Read now</button>
            </div>
        </div>)
}

export default Header