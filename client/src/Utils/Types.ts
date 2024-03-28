export interface User {
    email:string
    password:string
}
export interface BlogItemProps {
    owner: string;
    title: string;
    description: string;
    date: Date;
    avgReadingTime: string;
    categories: string[];
}
export interface TrendingBlogItemProps {
    title: string;
    owner: string;
    date: Date;
    avgReadingTime: string;
    categories: string[];
}