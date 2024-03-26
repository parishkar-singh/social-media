import type { Metadata } from "next";
import {Inter, Poppins, Ubuntu, Playfair_Display} from "next/font/google";
import "./globals.css";
import React from "react";

const inter = Inter({subsets: ['latin'], variable: '--font-inter'});
const poppins = Poppins({weight: ["400", "500"], subsets: ['latin'], variable: '--font-poppins'});
const ubuntu = Ubuntu({weight: ["400", "500"], subsets: ['latin'], variable: '--font-ubuntu'});
const playfair = Playfair_Display({weight: ["400", "500"], subsets: ['latin'], variable: '--font-playfair'});

export const metadata: Metadata = {
  title: "Blog Application using NextJS",
  description: "Spring-Boot, NextJS",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body className={` ${ubuntu.variable} ${inter.variable} ${playfair.variable} font-inter  h-screen w-screen bg-white dark:bg-black transition duration-200`}>{children}</body>
    </html>
  );
}
