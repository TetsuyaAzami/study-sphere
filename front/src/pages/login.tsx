import { FormEvent, useState } from "react";
import styles from "@/src/styles/login.module.css";
import { useUsername } from "@/src/hooks/useUsername";
import { usePassword } from "@/src/hooks/usePassword";
import axios from "axios";
import { useRouter } from "next/router";
import Head from "next/head";
import { TextInput } from "@/src/components/htmlElement/input/TextInput";
import { PasswordInput } from "@/src/components/htmlElement/input/PasswordInput";
import { Button } from "@/src/components/htmlElement/button/Button";
import { NextPage } from "next";

const Login: NextPage = () => {
  //
  const router = useRouter();

  const {
    username,
    isValid: isUsernameValid,
    invalidLengthMessage: usernameInvalidLengthMessage,
    maxLength: usernameMaxLength,
    handleChange: handleUsernameChange,
    handleBlur: handleUsernameBlur,
  } = useUsername();

  const {
    password,
    isValid: isPasswordValid,
    invalidLengthMessage: passwordInvalidLengthMessage,
    maxLength: passwordMaxLength,
    handleChange: handlePasswordChange,
    handleBlur: handlePasswordBlur,
  } = usePassword();

  const [loginErrorMessage, setLoginErrorMessage] = useState<string>("");

  const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
    //
    e.preventDefault();
    setLoginErrorMessage("");

    if (!isUsernameValid(username) || !isPasswordValid(password)) return;

    axios
      .post(
        "http://localhost:8080/api/login",
        { username, password },
        { withCredentials: true }
      )
      .then((res) => {
        //
        router.push("/");
      })
      .catch((err) => {
        //
        setLoginErrorMessage("ユーザ名またはパスワードが違います");
        console.error("エラーが発生しました");
        console.error("エラーメッセージ:", err.message);
        console.error("レスポンス:", err.response);
        console.error("リクエスト:", err.request);
        console.error("設定:", err.config);
      });
  };

  return (
    <div>
      <Head>
        <title>Login</title>
        <meta name="description" content="login page" />
      </Head>
      <div className={styles.container}>
        <form className={styles.form} onSubmit={handleSubmit}>
          <h1 className={styles.title}>ログイン</h1>
          <div className={styles["form-container"]}>
            <p className={styles.error}>{loginErrorMessage}</p>
            <TextInput
              label="ユーザ名"
              tag="username"
              value={username}
              onChange={handleUsernameChange}
              onBlur={handleUsernameBlur}
              required={true}
              maxLength={usernameMaxLength}
              errorMessage={usernameInvalidLengthMessage}
            />
            <PasswordInput
              label="パスワード"
              tag="password"
              value={password}
              onChange={handlePasswordChange}
              onBlur={handlePasswordBlur}
              required={true}
              maxLength={passwordMaxLength}
              errorMessage={passwordInvalidLengthMessage}
            />
            <Button
              type="submit"
              disabled={
                !isUsernameValid(username) || !isPasswordValid(password)
              }
              value="ログイン"
            />
          </div>
        </form>
      </div>
    </div>
  );
};

export default Login;
