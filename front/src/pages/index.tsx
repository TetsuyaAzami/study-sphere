import Head from "next/head";
import styles from "@/styles/Home.module.css";
import exp from "constants";
import { NextPage } from "next";

const Home: NextPage = () => {
  return (
    <div>
      <Head>
        <title>Home</title>
        <meta name="description" content="home page of login user" />
      </Head>
      <main></main>
    </div>
  );
};

export default Home;
