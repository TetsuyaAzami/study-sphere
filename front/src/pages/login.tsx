import { FormEvent } from "react";
import styles from "@/src/styles/login.module.css";
import { useUsername } from "@/src/hooks/useUsername";
import { usePassword } from "@/src/hooks/usePassword";

export default function Login() {
  //
  const {
    username,
    usernameInvalidLengthMessage,
    maxLength: usernameMaxLength,
    handleUsernameChange,
    handleUsernameBlur,
  } = useUsername();

  const {
    password,
    passwordInvalidLengthMessage,
    maxLength: passwordMaxLength,
    handlePasswordChange,
    handlePasswordBlur,
  } = usePassword();

  const handleSubmit = (e: FormEvent<HTMLElement>) => {
    //
    e.preventDefault();

    console.log(username, password);
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
          <button type="submit" className={styles.button}>
            ログイン
          </button>
        </div>
      </form>
    </div>
  );
}
