import { NextPage } from "next";
import Head from "next/head";
import styles from "@/src/styles/form.module.css";
import { TextInput } from "@/src/components/htmlElement/input/TextInput";
import { PasswordInput } from "@/src/components/htmlElement/input/PasswordInput";
import { Button } from "@/src/components/htmlElement/button/Button";
import { useUsername } from "@/src/hooks/useUsername";
import { usePassword } from "@/src/hooks/usePassword";

const Signup: NextPage = () => {
  const {
    username,
    isValid: isUsernameValid,
    maxLength: usernameMaxLength,
    invalidLengthMessage: usernameInvalidLengthMessage,
    handleChange: handleUsernameChange,
    handleBlur: handleUsernameBlur,
  } = useUsername();

  const {
    password,
    handleChange: handlePasswordChange,
    handleBlur: handlePasswordBlur,
    invalidLengthMessage: passwordInvalidLengthMessage,
    maxLength: passwordMaxLength,
    isValid: isPasswordValid,
  } = usePassword();

  const handleSubmit = () => {
    //
  };
  return (
    <div>
      <Head>
        <title>SignUp</title>
        <meta name="description" content="sign up page" />
      </Head>
      <div className={styles.container}>
        <form className={styles.form} onSubmit={handleSubmit}>
          <h1 className={styles.title}>ユーザ登録</h1>
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
            <Button
              type="submit"
              disabled={
                !isUsernameValid(username) || !isPasswordValid(password)
              }
              value="登録"
            />
          </div>
        </form>
      </div>
    </div>
  );
};

export default Signup;
