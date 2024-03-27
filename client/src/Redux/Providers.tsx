
'use client'
import { Provider } from "react-redux";
import { store } from "./store";
import React from "react";

interface ProvidersProps {
    children: React.ReactNode;
}

export const Providers: React.FC<ProvidersProps> = ({ children }) => {
    return <Provider store={store}>{children}</Provider>;
};