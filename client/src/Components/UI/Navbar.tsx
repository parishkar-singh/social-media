'use client'
import React, { useEffect, useState } from "react";
import ThemeToggle from "@/Components/ThemeToggle";
import { addTransition, darkModeBottomSeperator } from "@/Utils/DarkMode";
import NavbarLinks from "@/Components/Item/NavbarItem";

const Logo: React.FC = () => {
    return (
        <div className={`flex items-center`}>
            <div className={`bg-black dark:bg-white  rounded-full w-10 h-10 transform translate-z-20`}></div>
            <div className={`bg-black dark:bg-white  rounded-full w-10 h-10 skew-x-12 skew-y-12 skew-z-[24deg]`}></div>
            <div className={`bg-black dark:bg-white rounded-full w-10 h-10  skew-x-[50deg]`}></div>
            <span className={"font-playfair text-black dark:text-white font-black ml-4 text-3xl"}>High</span>
        </div>
    );
};
const SearchBar:React.FC=()=>{
    return (

        <form className="w-96  ">
            <label htmlFor="default-search"
                   className=" mb-2 text-sm font-medium text-gray-900 sr-only dark:text-white">Search</label>
            <div className="relative  ">
                <div className=" absolute inset-y-0 start-0 flex items-center ps-3 pointer-events-none">
                    <svg className="ml-1 w-4 h-5 text-gray-500 dark:text-gray-400" aria-hidden="true"
                         xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
                        <path stroke="currentColor" strokeLinecap="round" strokeLinejoin="round" strokeWidth="2"
                              d="m19 19-4-4m0-7A7 7 0 1 1 1 8a7 7 0 0 1 14 0Z"/>
                    </svg>
                </div>
                <input type="search" id="default-search"
                       className=" rounded-full block w-full p-3 ps-10 text-sm text-gray-900 border border-neutral-300 bg-gray-50 dark:bg-neutral-900 dark:border-neutral-900 outline-none dark:placeholder-gray-400 dark:text-white  "
                       placeholder="Search" />
                <button type="submit"
                        className=" rounded-full text-white absolute end-2.5 bottom-2.5 bg-primary  outline-none  font-medium  text-sm px-3 py-1 dark:bg-secondary ">Lets Read
                </button>
            </div>
        </form>

    )

}
const Navbar: React.FC = () => {
    const [scrolling, setScrolling] = useState(false);
    const [scrolledHalfway, setScrolledHalfway] = useState(false);

    useEffect(() => {
        const handleScroll = () => {
            const scrollTop = window.scrollY;
            const windowHeight = window.innerHeight;
            const documentHeight = document.body.scrollHeight;
            const scrollPercentage = (scrollTop / (documentHeight - windowHeight)) * 100;

            if (scrollPercentage > 15 && !scrolledHalfway) {
                setScrolledHalfway(true);
            } else if (scrollPercentage <= 15 && scrolledHalfway) {
                setScrolledHalfway(false);
            }

            if (scrollTop === 0) {
                setScrolling(false);
                setScrolledHalfway(false);
            } else if (!scrolling) {
                setScrolling(true);
            }
        };

        window.addEventListener("scroll", handleScroll);
        return () => {
            window.removeEventListener("scroll", handleScroll);
        };
    }, [scrolling, scrolledHalfway]);

    return (
        <div
            className={` fixed top-0 left-0 w-full flex items-center justify-between py-3 px-4 z-50 ${scrolling ? ' backdrop-blur-md backdrop-brightness-90 ' : ' bg-white dark:bg-black '} ${scrolledHalfway ? ' dark:border-b dark:border-b-neutral-600 border-b border-b-neutral-300 ' : ' '} ${addTransition}`}>
            <Logo />
            <div className={`flex items-center gap-4`}>
                <NavbarLinks />
                {/*<SearchBar/>*/}
                <ThemeToggle />
            </div>
        </div>
    );
};

export default Navbar;
