import type { Config } from "tailwindcss";

const config: Config = {
  darkMode: 'selector',
  content: [
    "./src/**/*.{js,ts,jsx,tsx,mdx}"
  ],
  theme: {
    extend: {
      fontFamily: {
        inter: ['var(--font-inter)'],
        ubuntu: ['var(--font-ubuntu)'],
        poppins: ['var(--font-poppins)'],
        playfair: ['var(--font-playfair)'],
        merri: ['var(--font-merri)'],
      },
      backgroundImage: {
        "gradient-radial": "radial-gradient(var(--tw-gradient-stops))",
        "gradient-conic":
          "conic-gradient(from 180deg at 50% 50%, var(--tw-gradient-stops))",
      },
      colors: {
        primary: '#FFC017',
        secondary: '#437AFF',
        third:'#7C3AED'
      }
    },
  },
  plugins: [],
};
export default config;
