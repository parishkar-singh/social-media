// Home.tsx
import React from "react";
import Navbar from "@/Components/UI/Navbar";
import Header from "@/Containers/Header";
import Trending from "@/Containers/Trending";
import Articles from "@/Containers/Articles";

export default function Home() {
    return (
        <main className=" max-w-screen max-h-screen">
                <Header/>
                <Trending />
                <Articles />
        </main>
    );
}
