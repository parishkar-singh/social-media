export interface User {
    email:string
    password:string
}
export interface BlogItemProps {
    ownerName: string;
    uploaderId:string
    title: string;
    description?: string;
    date?: Date;
    content?:string
    avgReadingTime: string;
    categories: string[];
    blogPic?:string
    commentIds?:string[];
    likes?:object[]
    visibility?:string
}
export interface TrendingBlogItemProps {
    title: string;
    ownerName: string;
    date: Date;
    avgReadingTime: string;
    categories: string[];
}