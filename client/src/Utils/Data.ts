import { BlogItemProps, TrendingBlogItemProps } from "@/Utils/Types";

function generateRandomIndex(arr: any[]): number {
    return Math.floor(Math.random() * arr.length);
}

export function generateBlogTestData(): BlogItemProps[] {
    const titles = [
        "The Art of Coding",
        "Exploring the World of Machine Learning",
        "Healthy Living: Tips for a Better Lifestyle",
        "The Culinary Journey: A Food Lover's Guide",
        "Mastering React: A Comprehensive Guide",
        "Travel Diaries: Adventures Across Continents"
    ];

    const owners = [
        "John Doe",
        "Jane Smith",
        "Alice Johnson",
        "Michael Brown",
        "Emily Wilson",
        "David Lee"
    ];

    const descriptions = [
        "Discover the beauty of coding and unleash your creativity with our comprehensive guide. Explore various programming languages, frameworks, and tools to become a proficient coder.",
        "Dive deep into the fascinating realm of machine learning and artificial intelligence. Learn about algorithms, models, and techniques used to build intelligent systems.",
        "Transform your life with simple yet effective health tips. Explore nutrition, exercise, mindfulness, and holistic wellness practices to achieve a better lifestyle.",
        "Embark on a mouthwatering journey through different cuisines from around the world. Explore recipes, cooking techniques, and cultural influences on food.",
        "Learn React from scratch and become a front-end ninja. Master the fundamentals, advanced concepts, and best practices for building modern web applications.",
        "Experience thrilling adventures and wanderlust tales from around the globe. Join us as we explore exotic destinations, uncover hidden gems, and embrace the spirit of travel."
    ];

    const dates = [
        new Date(2023, 0, 1),
        new Date(2023, 3, 15),
        new Date(2023, 6, 30),
        new Date(2023, 9, 10),
        new Date(2023, 11, 25),
        new Date(2023, 8, 5)
    ];

    const avgReadingTimes = ["2m","5m","6m", "10m", "15m", "20m"];

    const categories = [
        ["Technology", "Programming"],
        ["Travel", "Food", "Adventure"],
        ["Health", "Fitness", "Wellness"],
        ["Education", "Learning", "Improvement"],
        ["Cooking", "Recipes", "Food Culture"],
        ["React", "JavaScript", "Frontend"]
    ];

    const blogs: BlogItemProps[] = [];
    for (let i = 0; i < 10; i++) {
        const blog: BlogItemProps = {
            title: titles[generateRandomIndex(titles)],
            owner: owners[generateRandomIndex(owners)],
            description: descriptions[generateRandomIndex(descriptions)],
            date: dates[generateRandomIndex(dates)],
            avgReadingTime: avgReadingTimes[generateRandomIndex(avgReadingTimes)],
            categories: categories[generateRandomIndex(categories)]
        };
        blogs.push(blog);
    }
    return blogs;
}

export function generateTrendingBlogTestData(): TrendingBlogItemProps[] {
    const titles = [
        "The Trendy Tech: Latest Innovations in Technology",
        "2023 Travel Destinations: Explore the World's Wonders",
        "Health Hacks: Tips for a Fitter and Happier You",
        "Culinary Delights: Mouthwatering Recipes for Every Palate",
        "Frontend Fun: Dive into the World of Web Development",
        "Wanderlust Chronicles: Tales of Adventure and Discovery"
    ];

    const owners = [
        "John Doe",
        "Jane Smith",
        "Alice Johnson",
        "Michael Brown",
        "Emily Wilson",
        "David Lee"
    ];

    const dates = [
        new Date(2023, 0, 1),
        new Date(2023, 3, 15),
        new Date(2023, 6, 30),
        new Date(2023, 9, 10),
        new Date(2023, 11, 25),
        new Date(2023, 8, 5)
    ];



    const avgReadingTimes = ["5m", "10m", "15m", "20m"];

    const categories = [
        ["Technology", "Programming"],
        ["Travel", "Food", "Adventure"],
        ["Health", "Fitness", "Wellness"],
        ["Education", "Learning", "Improvement"],
        ["Cooking", "Recipes", "Food Culture"],
        ["React", "JavaScript", "Frontend "]
    ];

    const trendingBlogs: TrendingBlogItemProps[] = [];
    for (let i = 0; i <6; i++) {
        const trendingBlog: TrendingBlogItemProps = {
            title: titles[generateRandomIndex(titles)],
            owner: owners[generateRandomIndex(owners)],
            date: dates[generateRandomIndex(dates)],
            avgReadingTime: avgReadingTimes[generateRandomIndex(avgReadingTimes)],
            categories: categories[generateRandomIndex(categories)]
        };
        trendingBlogs.push(trendingBlog);
    }
    return trendingBlogs;
}
