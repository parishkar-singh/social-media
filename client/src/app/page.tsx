import Image from "next/image";
import ThemeToggle from "@/Components/ThemeToggle";
import React from "react";
import Navbar from "@/Components/UI/Navbar";
import Header from "@/Containers/Header";
import Trending from "@/Containers/Trending";
import BlogItem from "@/Components/Item/BlogItem";
import Articles from "@/Containers/Articles";

export default function Home() {
    return (
        <main className="flex flex-col  w-screen h-screen   ">
            <Navbar/>
            <Header/>
            {/*<Trending/>*/}
            <Articles/>
        </main>
    );
}
