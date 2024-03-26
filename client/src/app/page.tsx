import Image from "next/image";
import ThemeToggle from "@/Components/ThemeToggle";
import React from "react";
import Navbar from "@/Components/UI/Navbar";

export default function Home() {
  return (
    <main className="flex flex-col  w-full h-screen items-center justify-between ">
      <Navbar/>

    </main>
  );
}
