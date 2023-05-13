import { FormEvent } from "react";
import styles from "@/src/styles/login.module.css";
import { useUsername } from "@/src/hooks/useUsername";
import { usePassword } from "@/src/hooks/usePassword";
import axios from "axios";
import { useRouter } from "next/router";
import Head from "next/head";
import { TextInput } from "@/src/components/input/TextInput";
import { PasswordInput } from "@/src/components/input/PasswordInput";

export default function Login() {
  //
  const router = useRouter();

  const {
    username,
    isUsernameValid,
    usernameInvalidLengthMessage,
    maxLength: usernameMaxLength,
    handleUsernameChange,
    handleUsernameBlur,
  } = useUsername();

  const {
    password,
    isPasswordValid,
    passwordInvalidLengthMessage,
    maxLength: passwordMaxLength,
    handlePasswordChange,
    handlePasswordBlur,
  } = usePassword();

  const handleSubmit = (e: FormEvent<HTMLFormElement>) => {
    //
    e.preventDefault();

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
            <button
              type="submit"
              className={styles.button}
              disabled={
                !isUsernameValid(username) || !isPasswordValid(password)
              }
            >
              ログイン
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
