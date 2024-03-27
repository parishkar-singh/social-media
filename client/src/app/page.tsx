// Home.tsx
import React from "react";
import Navbar from "@/Components/UI/Navbar";
import Header from "@/Containers/Header";
import Trending from "@/Containers/Trending";
import Articles from "@/Containers/Articles";

export default function Home() {
    return (
        <div className="max-w-screen max-h-screen">
            <Navbar />
            <main className=" mt-[65px] ">
                <Header />
                <Trending />
                <Articles />
            </main>
        </div>
    );
}
