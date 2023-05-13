import { FormEvent } from "react";
import styles from "@/src/styles/login.module.css";
import { useUsername } from "@/src/hooks/useUsername";
import { usePassword } from "@/src/hooks/usePassword";
import axios from "axios";
import { useRouter } from "next/router";
import Head from "next/head";

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

  };

  return (
    <div className={styles.container}>
      <form className={styles.form} onSubmit={handleSubmit}>
        <h1 className={styles.title}>ログイン</h1>
        <div className={styles["form-container"]}>
          <div className={styles["input-container"]}>
            <label htmlFor="username">ユーザ名: </label>
            <input
              id="username"
              type="text"
              name="username"
              value={username}
              placeholder="username"
              onChange={handleUsernameChange}
              onBlur={handleUsernameBlur}
              required
              className={styles.input}
              maxLength={usernameMaxLength}
            />
            {usernameInvalidLengthMessage && (
              <p className={styles.error}>{usernameInvalidLengthMessage}</p>
            )}
          </div>
          <div className={styles["input-container"]}>
            <label htmlFor="password">パスワード: </label>
            <input
              id="password"
              type="password"
              name="password"
              value={password}
              placeholder="password"
              onChange={handlePasswordChange}
              onBlur={handlePasswordBlur}
              required
              className={styles.input}
              maxLength={passwordMaxLength}
            />
          </div>
          {passwordInvalidLengthMessage && (
            <p className={styles.error}>{passwordInvalidLengthMessage}</p>
          )}
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
  );
}
