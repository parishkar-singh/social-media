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

const Navbar: React.FC = () => {
    const [scrolling, setScrolling] = useState(false);

    useEffect(() => {
        const handleScroll = () => {
            const scrollTop = window.scrollY;
            if (scrollTop > 0 && !scrolling) {
                setScrolling(true);
            } else if (scrollTop === 0 && scrolling) {
                setScrolling(false);
            }
        };

        window.addEventListener("scroll", handleScroll);
        return () => {
            window.removeEventListener("scroll", handleScroll);
        };
    }, [scrolling]);

    return (
        <div className={`fixed top-0 left-0 w-full flex items-center justify-between py-3 px-4 z-10 ${scrolling ? ' backdrop-blur-md ' : 'bg-primary dark:bg-violet-600'}  ${addTransition}` +darkModeBottomSeperator}>
            <Logo />
            <div className={`flex items-center gap-4`}>
                <NavbarLinks />
                <ThemeToggle />
            </div>
        </div>
    );
};

export default Navbar;
